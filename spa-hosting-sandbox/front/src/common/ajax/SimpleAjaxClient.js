

class SimpleAjaxClient {

  buildUrl(url) {
    return `${location.origin}${import.meta.env.BASE_URL}${url}`
  }

  async get(url, data) {
    const p = new URLSearchParams(data)
    const resp = await fetch(`${this.buildUrl(url)}?${p.toString()}`, {
      method: "GET",
    })
    return await resp.json()
  }

  async post(url, data) {
    const resp = await fetch(this.buildUrl(url), {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    })
    return await resp.json()
  }
}


export {
  SimpleAjaxClient
}