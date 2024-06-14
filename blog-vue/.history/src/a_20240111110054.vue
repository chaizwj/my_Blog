<template>
    <div>
        <button @click="sortArray">Sort Array</button>
        <ul>
            <li v-for="num in numbers" :key="num">{{ num }}</li>
        </ul>
    </div>
</template>

<script>
export default {
    data() {
        return {
            numbers: [5, 3, 8, 1, 2],
        };
    },
    methods: {
        sortArray() {
            let len = this.numbers.length;
            for (let i = 0; i < len - 1; i++) {
                for (let j = 0; j < len - 1 - i; j++) {
                    if (this.numbers[j] > this.numbers[j + 1]) {
                        let temp = this.numbers[j];
                        this.numbers[j] = this.numbers[j + 1];
                        this.numbers[j + 1] = temp;
                    }
                }
            }
        },
    },
};
</script>
