import { createWebHistory, createRouter } from 'vue-router';

export const publicRoute = [
	{
		path: '/',
		name: 'home',
		component: () => import('~/src/view/Home')
	}
];

const router = createRouter({
	history: createWebHistory(),
	routes: publicRoute,
});

export default router;