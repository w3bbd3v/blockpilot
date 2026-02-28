# BlockPilot

BlockPilot is a control plane for the [BLOCKCLOCK mini](https://blockclockmini.com/) — a Bitcoin-focused E-Ink display device. It exposes the full BLOCKCLOCK API through a clean UI and adds automation on top: schedule commands to run on a cron or custom time interval, so your clock always shows exactly what you want, when you want it.

## What it does

The BLOCKCLOCK mini is a LAN-connected device with an HTTP API. BlockPilot acts as a controller that sits between you and the device, providing:

- **On-demand control** — issue any BLOCKCLOCK command from a web UI without crafting raw HTTP requests
- **Scheduled automation** — configure commands to run automatically on a cron schedule or custom interval
- **Full API coverage** — supports all BLOCKCLOCK mini API categories:
  - Display: show numbers, text, and images
  - Lighting: set LED colours, flash effects, off
  - Data selection: pick and cycle through data tags (BTC price, block height, etc.)
  - Actions: pause updates, set update rate, reboot, power down
  - Audio: control tick sounds (MICRO model)
  - Status: query current device state
- **Third-party data sources** — fetch live data from external APIs (stock prices, exchange rates, custom feeds) and push it straight to the display. Any data you can get from an HTTP endpoint can be formatted and shown on the clock.

### Example: custom stock ticker

BlockPilot fetches the current price of E-Corp stock from a third-party market data API, then pushes it to the BLOCKCLOCK as a two-call sequence:

```
GET http://<blockclock-ip>/api/ou_text/0/E-/CORP
GET http://<blockclock-ip>/api/show/number/142.57?sym=$
```

The first call places `E-` above and `CORP` below digit slot 0, rendering the label at a larger size than corner decorators like `tl=`. The second call sets the price display — the `ou_text` decoration on slot 0 persists alongside it.

This illustrates a core BlockPilot concept: a **command** is a named, reusable sequence of one or more BlockClock API calls that are fired together as a single logical action. The whole flow — fetch price, format, push sequence — can be triggered on demand from the UI or run automatically on a schedule.

## Architecture

| Module                          | Description                                                              |
|---------------------------------|--------------------------------------------------------------------------|
| `blockpilot-server`             | Spring Boot backend — BLOCKCLOCK API client, scheduling engine, REST API |
| `blockpilot-client` _(planned)_ | Frontend UI for on-demand control and schedule management                |

## Requirements

- Java 21
- A BLOCKCLOCK mini on the same LAN
- Gradle (wrapper included — use `./gradlew`)

## Getting started

```bash
./gradlew :blockpilot-server:bootRun
```

## Development

Build and test all modules from the repo root:

```bash
./gradlew build
```

Run server tests only:

```bash
./gradlew :blockpilot-server:test
```
