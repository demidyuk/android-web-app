<template>
  <div class="h-100">
    <div class="d-flex align-items-center justify-content-center h-100">
      <div class="text-center">
        <div class="p-2 bg-primary mb-3 text-white rounded-lg">First Page</div>
        <b-button :to="'/second'" variant="outline-primary">go to second</b-button>
      </div>
    </div>
    <div class="position-absolute bottom-0 w-100 mb-5">
      <div class="d-flex justify-content-center">
        <div class="p-2 rounded-lg bg-secondary text-white">views: {{views}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FirstPage',
  mounted() {
    let views = parseInt(localStorage.getItem('views')) || 0;
    views++;
    localStorage.setItem('views', views);
    this.views = views;
  },
  data() {
    return {
      views: 0,
    };
  },
};
</script>


