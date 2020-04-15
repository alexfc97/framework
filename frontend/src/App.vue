<template>
  <div id="main">
    <MainPage/>
  </div>
</template>

<script>
  import MainPage from "./components/MainPage";
  import 'bootstrap/dist/css/bootstrap.css';
  import 'bootstrap-vue/dist/bootstrap-vue.css';


  export default {
    name: 'App',
    components: {
      MainPage
    }
  }
</script>

<style>

</style>
