<template>
  <div>
    <!-- banner -->
    <div class="message-banner">
      <!-- 弹幕输入框 -->
      <div class="message-container">
        <h1 class="message-title">留言板</h1>
        <div class="animated fadeInUp message-input-wrapper">
          <input
            v-model="messageContent"
            @click="show = true"
            placeholder="说点什么吧" style="background: transparent;"
          />
          <button
            class="ml-3 animated bounceInLeft"
            @click="addToList"
            v-show="show"
          >
            发送
          </button>
        </div>
      </div>
      <!-- 弹幕列表 -->
      <div class="barrage-container">
        <vue-baberrage :barrageList="barrageList" :loop="true">
          <template v-slot:default="slotProps">
            <span class="barrage-items">
              <img
                :src="slotProps.item.avatar"
                width="30"
                height="30"
                style="border-radius:50%"
              />
              <span class="ml-2">{{ slotProps.item.nickname }} :</span>
              <span class="ml-2">{{ slotProps.item.messageContent }}</span>
            </span>
          </template>
        </vue-baberrage>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import { vueBaberrage } from 'vue-baberrage'
Vue.use(vueBaberrage)
export default {
  created () {
    this.avatar = this.$store.state.avatar
    this.nickname = this.$store.state.nickname
  },
  mounted () {
    this.listMessage()
  },
  data () {
    return {
      avatar: '',
      nickname: '',
      show: false,
      messageContent: '',
      barrageList: []
    }
  },
  methods: {
    async addToList () {
      if (this.messageContent.trim() === '') {
        this.$message.info('留言不能为空')
        return false
      }
      const userAvatar = this.avatar
        ? this.avatar
        : 'https://r.photo.store.qq.com/psc?/V53KcXfb1umonn4HbITu3rINxs43TczD/45NBuzDIW489QBoVep5mccJUo7*q6gaMPZmbFDSW8tjmAm4XwuoUZmMKw3asmvn1mxsE*Tf0fj.VOh2G6OX7v4duFOfedV2oGNQ*GrJEPkA!/r'
      const userNickname = this.nickname
        ? this.nickname
        : ''
      const message = {
        avatar: userAvatar,
        nickname: userNickname,
        messageContent: this.messageContent,
        time: Math.floor(Math.random() * 10 + 6)
      }
      this.barrageList.push(message)
      this.messageContent = ''
      const { data: res } = await this.$http.post('/api/server/message/add', message)
      // const { data: res } = await this.$http.post('/extension/message/add', message)
      if (res.flag) {
        // 弹出提示信息
        this.$message.success('新增留言成功')
      } else { // 执行失败
        this.$message.error('留言失败')
      }
    },
    async listMessage () {
      const { data: res } = await this.$http.get('/api/server/message/getMessageList')
      // const { data: res } = await this.$http.get('/extension/message/getMessageList')
      if (res.flag) {
        // this.$message.success(res.message)
        this.barrageList = res.data
      } else {
        this.$message.error(res.message)
      }
    }
  }
}
</script>

<style scoped>
  .message-banner {
    position: absolute;
    /*top: -58px;*/
    left: 0;
    right: 0;
    height: 100vh;
    background: url("../assets/images/1.jpg")center center /
    cover no-repeat;
    background-color: #49b1f5;
    animation: header-effect 1s;
  }
  .message-title {
    color: #eee;
    animation: title-scale 1s;
  }
  .message-container {
    position: absolute;
    width: 360px;
    top: 35%;
    left: 0;
    right: 0;
    text-align: center;
    z-index: 5;
    margin: 0 auto;
    color: #fff;
  }
  .message-input-wrapper {
    display: flex;
    justify-content: center;
    height: 2.5rem;
    margin-top: 2rem;
  }
  .message-input-wrapper input {
    outline: none;
    width: 70%;
    border-radius: 20px;
    height: 100%;
    padding: 0 1.25rem;
    color: #eee;
    border: #fff 1px solid;
  }
  .message-input-wrapper input::-webkit-input-placeholder {
    color: #eeee;
  }
  .message-input-wrapper button {
    outline: none;
    border-radius: 20px;
    height: 100%;
    padding: 0 1.25rem;
    border: #fff 1px solid;
  }
  .barrage-container {
    position: absolute;
    top: 50px;
    left: 0;
    right: 0;
    bottom: 0;
    height: calc(100% - 50px);
    width: 100%;
  }
  .barrage-items {
    background: rgb(0, 0, 0, 0.7);
    border-radius: 100px;
    color: #fff;
    padding: 5px 10px 5px 5px;
    align-items: center;
    display: flex;
  }
</style>
