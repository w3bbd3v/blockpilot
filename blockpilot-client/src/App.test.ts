import { describe, it, expect, vi, beforeEach } from 'vitest'
import { mount, flushPromises } from '@vue/test-utils'
import App from './App.vue'
import * as displayApi from './api/displayApi'

vi.mock('./api/displayApi')

describe('App', () => {
  beforeEach(() => {
    vi.mocked(displayApi.setColor).mockResolvedValue(undefined)
  })

  it('renders all preset color buttons', () => {
    const wrapper = mount(App)
    const buttons = wrapper.findAll('.presets button')

    expect(buttons).toHaveLength(5)
    expect(buttons[0].text()).toBe('Red')
    expect(buttons[4].text()).toBe('Off')
  })

  it('calls setColor with the correct hex when a preset button is clicked', async () => {
    const wrapper = mount(App)
    await wrapper.find('.presets button').trigger('click')
    await flushPromises()

    expect(displayApi.setColor).toHaveBeenCalledWith('FF0000')
  })

  it('shows a status message after a successful send', async () => {
    const wrapper = mount(App)
    await wrapper.find('.presets button').trigger('click')
    await flushPromises()

    expect(wrapper.find('.status').text()).toBe('Sent #FF0000')
  })

  it('shows an error message when setColor throws', async () => {
    vi.mocked(displayApi.setColor).mockRejectedValue(new Error('Network error'))

    const wrapper = mount(App)
    await wrapper.find('.presets button').trigger('click')
    await flushPromises()

    expect(wrapper.find('.status').text()).toBe('Network error')
  })
})
