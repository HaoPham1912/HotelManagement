<template>
  <div
    :class="[ theme, { 'cross': !isInHamburgerState }]"
    @click="onClick"
  >
    <div class="bar1" />
    <div class="bar2" />
    <div class="bar3" />
  </div>
</template>
<script>
export default {
  name: 'HamburgerButton',
  props: {
    isHamburger: {
      type: Boolean,
      default: true
    },
    theme: {
      type: String,
      default: 'default-theme'
    }
  },
  data () {
    return {
      isInHamburgerState: this.isHamburger
    }
  },
  methods: {
    onClick () {
      this.isInHamburgerState = !this.isInHamburgerState
      this.$emit('click', this.isInHamburgerState)
    }
  }
}
</script>
<style lang="scss">
</style>

