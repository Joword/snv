import compression from 'vite-plugin-compression'

export default function createCompression(env) {
	const { VITE_BUILD_COMPRESS } = env
	const compressList = VITE_BUILD_COMPRESS.split(',')
	const plugin = []
	if (compressList.includes('gzip')) {
		// http://doc.ruoyi.vip/ruoyi-vue/other/faq.html#ʹ��gzip��ѹ����̬�ļ�
		plugin.push(
				compression({
					ext: '.gz',
					deleteOriginFile: false
				})
		)
	}
	if (compressList.includes('brotli')) {
		plugin.push(
				compression({
					ext: '.br',
					algorithm: 'brotliCompress',
					deleteOriginFile: false
				})
		)
	}
	return plugin
}