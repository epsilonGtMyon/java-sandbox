package epsilongtmyon;

import epsilongtmyon.setting.EnvironmentSetting;
import epsilongtmyon.setting.PropertiesFileSetting;
import epsilongtmyon.setting.Setting;
import epsilongtmyon.setting.SettingComposite;
import epsilongtmyon.setting.SystemPropertiesSetting;

public class Usage {

	public static void main(String[] args) {
		Setting setting = prepareSetting();
		
		System.out.println(setting.getProperty("user.home"));
		System.out.println(setting.getProperty("JAVA_HOME"));
		System.out.println(setting.getProperty("key1"));
	}

	private static Setting prepareSetting() {

		SettingComposite sc = new SettingComposite();
		sc.addSetting(new SystemPropertiesSetting());
		sc.addSetting(new EnvironmentSetting());
		sc.addSetting(new PropertiesFileSetting("setting.properties"));
		return sc;
	}
}
