export async function setColor(rgbHex: string): Promise<void> {
  const response = await fetch('/api/display/color', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ color: rgbHex }),
  })

  if (!response.ok) {
    throw new Error(`Failed to set color: ${response.status}`)
  }
}
