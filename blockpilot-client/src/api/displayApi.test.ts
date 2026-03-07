import { describe, it, expect, vi, beforeEach } from 'vitest'
import { setColor } from './displayApi'

describe('setColor', () => {
  beforeEach(() => {
    vi.stubGlobal('fetch', vi.fn())
  })

  it('POSTs to /api/display/color with the hex color', async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 200 }))

    await setColor('FF0000')

    expect(fetch).toHaveBeenCalledWith('/api/display/color', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ color: 'FF0000' }),
    })
  })

  it('throws when response is not ok', async () => {
    vi.mocked(fetch).mockResolvedValue(new Response(null, { status: 500 }))

    await expect(setColor('FF0000')).rejects.toThrow('Failed to set color: 500')
  })
})
