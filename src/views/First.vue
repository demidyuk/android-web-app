<template>
  <div class="d-flex align-items-center justify-content-center flex-column h-100">
    <div class="text-center">
      <div class="p-2 bg-primary mb-3 text-white rounded-lg">First Page</div>
      <b-button :to="'/second'" variant="outline-primary">go to second</b-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FirstPage',
};
</script>


