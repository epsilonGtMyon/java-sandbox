package epsilongtmyon.spec.section.section06;

import java.util.Locale;
import java.util.Objects;

import jakarta.validation.MessageInterpolator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;


// 別のリソースバンドルから読み込むやつ
public class Section06MessageInterpolator implements MessageInterpolator {

	private final MessageInterpolator custom;

	private final MessageInterpolator delegate;

	public Section06MessageInterpolator(MessageInterpolator delegate) {
		super();
		this.custom = new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("Section06"));
		this.delegate = delegate;
	}

	@Override
	public String interpolate(String messageTemplate, Context context) {
		return this.interpolate(messageTemplate, context, Locale.getDefault());
	}

	@Override
	public String interpolate(String messageTemplate, Context context, Locale locale) {
		String interpolated = custom.interpolate(messageTemplate, context, locale);
		if (!Objects.equals(messageTemplate, interpolated)) {
			return interpolated;
		}
		
		return this.delegate.interpolate(messageTemplate, context, locale);
	}

}
