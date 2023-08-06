package epsilongtmyon.setting;

import java.util.ArrayList;
import java.util.List;

public class SettingComposite implements Setting {

	private final List<Setting> settings = new ArrayList<>();

	public SettingComposite() {
	}

	public void addSetting(Setting setting) {
		settings.add(setting);
	}

	@Override
	public String getProperty(String key) {
		for (Setting setting : settings) {
			String value = setting.getProperty(key);
			if (value != null) {
				return value;
			}
		}
		return null;
	}

}
