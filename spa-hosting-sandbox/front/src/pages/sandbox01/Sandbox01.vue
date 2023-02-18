<script setup>
import {ref} from "vue"
import { SimpleAjaxClient } from "../../common/ajax/SimpleAjaxClient";

const ajax = new SimpleAjaxClient();

const value1 = ref("あ")
const response = ref("")

const doGet = async () => {
  const r = await ajax.get("api/sandbox01", {value01: value1.value})
  response.value = JSON.stringify(r)
}

const doPost = async () => {
  const r = await ajax.post("api/sandbox01", {value01: value1.value})
  response.value = JSON.stringify(r)
}

</script>

<template>
  <div>
    sandbox01
    <div>
      <label>
        value01:
        <input type="text" v-model="value1" />
      </label>
    </div>
    <div>
      <button @click="doGet">get</button>
      <button @click="doPost">post</button>
    </div>
    <pre>
      {{ response }}
    </pre>
  </div>
</template>