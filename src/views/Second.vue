<template>
  <div class="d-flex align-items-center justify-content-center flex-column h-100">
    <div class="text-center">
      <div class="p-2 bg-primary mb-3 text-white rounded-lg">Second Page</div>
      <b-button :to="'/'" variant="outline-primary">go to first</b-button>
    </div>
  </div>
</template>

<script>
import { showToast } from '@/lib/android';

export default {
  name: 'SecondPage',
  methods: {
    showToast() {
      showToast('Second page!');
    },
  },
  mounted() {
    this.showToast();
  },
};
</script>
