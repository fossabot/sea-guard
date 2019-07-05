<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <a-button type="primary">
      Button
    </a-button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: {
      type: String,
      required: true
    }
  }
};
</script>

<style scoped lang="less">
h3 {
  margin: 40px 0 0;
}
</style>
