import { defineConfig, loadEnv } from 'vite';
import createVitePlugins from "./vite";
import path from "path";

// https://vitejs.dev/config/
export default defineConfig(({ command, mode }) => {
	const env = loadEnv(mode, process.cwd(), "")
	return {
		plugins: createVitePlugins(env, command === 'build:prod'),
		resolve: {
			// https://cn.vitejs.dev/config/#resolve-alias
			alias: {
				// 设置路径
				'~': path.resolve(__dirname, './'),
				// 设置别名
				'@': path.resolve(__dirname, './src'),
				'/@components/': path.resolve(__dirname, './src/components')
			},
			// https://cn.vitejs.dev/config/#resolve-extensions
			extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
		},
		// vite服务相关配置
		server: {
			port: 9211,
			host: true,
			open: true,
			proxy: {
				// https://cn.vitejs.dev/config/#server-proxy
				'/midApi': {
					target: 'http://127.0.0.1:9221',
					changeOrigin: true,
					rewrite: (p) => p.replace(/^\/midApi/, '')
				}
			},
		},
	}
})
