import { createRouter, createWebHistory } from "vue-router";

import Home from "../../pages/home/Home.vue";
import Sandbox01 from "../../pages/sandbox01/Sandbox01.vue";

const routes = [
  {
    path: "/",
    redirect: "/home",
  },
  {
    path: "/home",
    name: "home",
    component: Home,
  },
  {
    path: "/sandbox01",
    name: "sandbox01",
    component: Sandbox01,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export { router };
