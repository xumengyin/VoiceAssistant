package com.jerry.voiceassistant.bdtts;

import android.content.Context;
import android.util.Pair;

import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.jerry.voiceassistant.Constants;
import com.jerry.voiceassistant.utils.Logs;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TtsHelper
{
	// TtsMode.MIX; 离在线融合，在线优先； TtsMode.ONLINE 纯在线； TtsMode.OFFLINE 纯离线合成，需要纯离线SDK
	protected TtsMode ttsMode = IOfflineResourceConst.DEFAULT_OFFLINE_TTS_MODE;
	// 离线发音选择，VOICE_FEMALE即为离线女声发音。
	protected String offlineVoice = OfflineResource.VOICE_FEMALE;
	Context context;
	// 主控制类，所有合成控制方法从这个类开始
	protected MySyntherizer synthesizer;
	protected String appId;

	protected String appKey;

	protected String secretKey;
	static TtsHelper ttsManager;

	public static TtsHelper getInstance(Context context)
	{
		if (ttsManager == null)
		{
			ttsManager = new TtsHelper(context.getApplicationContext());
		}
		return ttsManager;
	}

	public TtsHelper(Context context)
	{
		this.context = context;
		init();
	}

	void init()
	{
		appId = Constants.baiDuAppId;
		appKey = Constants.baiDuApiKey;
		secretKey = Constants.baiDuSecretKey;
		initialTts();
	}

	protected void initialTts()
	{
		//LoggerProxy.printable(true); // 日志打印在logcat中
		// 设置初始化参数
//		// 此处可以改为 含有您业务逻辑的SpeechSynthesizerListener的实现类
//		SpeechSynthesizerListener listener = new UiMessageListener(mainHandler);
		InitConfig config = getInitConfig(null);
		synthesizer = new NonBlockSyntherizer(context, config, null); // 此处可以改为MySyntherizer 了解调用过程
	}

	protected InitConfig getInitConfig(SpeechSynthesizerListener listener)
	{
		Map<String, String> params = getParams();
		// 添加你自己的参数
		InitConfig initConfig;
		// appId appKey secretKey 网站上您申请的应用获取。注意使用离线合成功能的话，需要应用中填写您app的包名。包名在build.gradle中获取。

		initConfig = new InitConfig(appId, appKey, secretKey, ttsMode, params, listener);

		// 如果您集成中出错，请将下面一段代码放在和demo中相同的位置，并复制InitConfig 和 AutoCheck到您的项目中
		// 上线时请删除AutoCheck的调用
//		AutoCheck.getInstance(getApplicationContext()).check(initConfig, new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				if (msg.what == 100) {
//					AutoCheck autoCheck = (AutoCheck) msg.obj;
//					synchronized (autoCheck) {
//						String message = autoCheck.obtainDebugMessage();
//						toPrint(message); // 可以用下面一行替代，在logcat中查看代码
//						// Log.w("AutoCheckMessage", message);
//					}
//				}
//			}
//
//		});
		return initConfig;
	}

	/**
	 * 合成的参数，可以初始化时填写，也可以在合成前设置。
	 *
	 * @return 合成参数Map
	 */
	protected Map<String, String> getParams()
	{
		Map<String, String> params = new HashMap<>();
		// 以下参数均为选填
		// 设置在线发声音人： 0 普通女声（默认） 1 普通男声 2 特别男声 3 情感男声<度逍遥> 4 情感儿童声<度丫丫>, 其它发音人见文档
		params.put(SpeechSynthesizer.PARAM_SPEAKER, "0");
		// 设置合成的音量，0-15 ，默认 5
		params.put(SpeechSynthesizer.PARAM_VOLUME, "15");
		// 设置合成的语速，0-15 ，默认 5
		params.put(SpeechSynthesizer.PARAM_SPEED, "5");
		// 设置合成的语调，0-15 ，默认 5
		params.put(SpeechSynthesizer.PARAM_PITCH, "5");

		params.put(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_HIGH_SPEED_NETWORK);
		// 该参数设置为TtsMode.MIX生效。即纯在线模式不生效。
		// MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
		// MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
		// MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
		// MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线

		// params.put(SpeechSynthesizer.PARAM_MIX_MODE_TIMEOUT, SpeechSynthesizer.PARAM_MIX_TIMEOUT_TWO_SECOND);
		// 离在线模式，强制在线优先。在线请求后超时2秒后，转为离线合成。

		// 离线资源文件， 从assets目录中复制到临时目录，需要在initTTs方法前完成
//		OfflineResource offlineResource = createOfflineResource(offlineVoice);
//		if(offlineResource!=null)
//		{
//			// 声学模型文件路径 (离线引擎使用), 请确认下面两个文件存在
//			params.put(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, offlineResource.getTextFilename());
//			params.put(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE,
//					offlineResource.getModelFilename());
//		}
		return params;
	}

	protected OfflineResource createOfflineResource(String voiceType)
	{
		OfflineResource offlineResource = null;
		try
		{
			offlineResource = new OfflineResource(context, voiceType);
		} catch (IOException e)
		{
			Logs.d("xuxuxu", "createOfflineResource: "+e.getMessage());
			// IO 错误自行处理
			e.printStackTrace();
			//toPrint("【error】:copy files from assets failed." + e.getMessage());
		}
		return offlineResource;
	}

	/**
	 * speak 实际上是调用 synthesize后，获取音频流，然后播放。
	 * 获取音频流的方式见SaveFileActivity及FileSaveListener
	 * 需要合成的文本text的长度不能超过1024个GBK字节。
	 */
	public void speak(String text)
	{
		// 合成前可以修改参数：
		// Map<String, String> params = getParams();
		// params.put(SpeechSynthesizer.PARAM_SPEAKER, "3"); // 设置为度逍遥
		// synthesizer.setParams(params);
		int result = synthesizer.speak(text);
	}

	/**
	 * 合成但是不播放，
	 * 音频流保存为文件的方法可以参见SaveFileActivity及FileSaveListener
	 */
	public void synthesize(String text)
	{

		int result = synthesizer.synthesize(text);
		//checkResult(result, "synthesize");
	}

	/**
	 * 批量播放
	 */
	private void batchSpeak(List<String> textlist)
	{
		List<Pair<String, String>> texts = new ArrayList<>();
		for (int i = 0; i < textlist.size(); i++)
		{
			texts.add(new Pair<>(textlist.get(i), i + ""));
		}
		int result = synthesizer.batchSpeak(texts);
	}
}
