import { createWebHistory, createWebHashHistory, createRouter } from 'vue-router';

export const publicRoute = [
	{
		path: '/',
		name: 'home',
		component: () => import('~/src/views/HomeView')
	}
];

const router = createRouter({
	history: createWebHistory(),
	routes: publicRoute,
});

export default router;