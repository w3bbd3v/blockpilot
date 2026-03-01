<script setup lang="ts">
import { ref } from 'vue'
import { setColor } from './api/displayApi'

const pickedColor = ref('#ff0000')
const status = ref('')

const presets = [
  { label: 'Red',   hex: 'FF0000' },
  { label: 'Green', hex: '00FF00' },
  { label: 'Blue',  hex: '0000FF' },
  { label: 'White', hex: 'FFFFFF' },
  { label: 'Off',   hex: '000000' },
]

async function send(hex: string) {
  status.value = ''
  try {
    await setColor(hex)
    status.value = `Sent #${hex}`
  } catch (e) {
    status.value = e instanceof Error ? e.message : 'Error'
  }
}

async function sendPicked() {
  await send(pickedColor.value.replace('#', ''))
}
</script>

<template>
  <main>
    <h1>BlockPilot</h1>

    <section>
      <h2>Display Color</h2>

      <div class="presets">
        <button v-for="p in presets" :key="p.hex" @click="send(p.hex)">
          {{ p.label }}
        </button>
      </div>

      <div class="custom">
        <input type="color" v-model="pickedColor" />
        <button @click="sendPicked">Set</button>
      </div>

      <p v-if="status" class="status">{{ status }}</p>
    </section>
  </main>
</template>

<style>
body {
  font-family: system-ui, sans-serif;
  max-width: 480px;
  margin: 2rem auto;
  padding: 0 1rem;
}

h1 { margin-bottom: 0.25rem; }

.presets {
  display: flex;
  gap: 0.5rem;
  flex-wrap: wrap;
  margin-bottom: 1rem;
}

.custom {
  display: flex;
  gap: 0.5rem;
  align-items: center;
}

.status {
  margin-top: 0.75rem;
  font-size: 0.875rem;
  color: #555;
}
</style>
