<template>
  <div class="h-100">
    <div class="position-absolute w-100">
      <div class="d-flex justify-content-center mt-4">
        <div class="d-flex align-items-center">
          <b>current path:</b>
          <div class="bg-primary text-white ml-2 p-2 rounded-lg">{{getPath()}}</div>
        </div>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app',
  methods: {
    getPath() {
      return window.location.pathname;
    },
  },
};
</script>

<style>
html,
body {
  height: 100%;
}

.bottom-0 {
  bottom: 0;
}
</style>
