package epsilongtmyon.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellUtil;

/**
 * {@link CellUtil#setCellStyleProperties(Cell, Map)}を呼ぶためのサポートクラス
 */
public class CellStyleMapBuilder {

	private final Map<String, Object> cellStyleMap;

	private CellStyleMapBuilder(Map<String, Object> cellStyleMap) {
		this.cellStyleMap = cellStyleMap;
	}

	public static CellStyleMapBuilder builder() {
		return builder(new HashMap<>());
	}

	public static CellStyleMapBuilder builder(Map<String, Object> cellStyleMap) {
		return new CellStyleMapBuilder(cellStyleMap);
	}

	public Map<String, Object> build() {
		return Collections.unmodifiableMap(cellStyleMap);
	}

	/**
	 * 対象のセルに{@link CellUtil#setCellStyleProperties(Cell, Map)}を実行します。
	 * 
	 * @param cell 対象のセル
	 */
	public void setCellStyleToCell(Cell cell) {
		CellUtil.setCellStyleProperties(cell, build());
	}

	//----------------
	// cf: CelUtil#getFormatProperties

	public CellStyleMapBuilder alignment(HorizontalAlignment value) {
		cellStyleMap.put(CellUtil.ALIGNMENT, value);
		return this;
	}

	public CellStyleMapBuilder verticalAlignment(VerticalAlignment value) {
		cellStyleMap.put(CellUtil.VERTICAL_ALIGNMENT, value);
		return this;
	}

	public CellStyleMapBuilder borderBottom(BorderStyle value) {
		cellStyleMap.put(CellUtil.BORDER_BOTTOM, value);
		return this;
	}

	public CellStyleMapBuilder borderLeft(BorderStyle value) {
		cellStyleMap.put(CellUtil.BORDER_LEFT, value);
		return this;
	}

	public CellStyleMapBuilder borderRight(BorderStyle value) {
		cellStyleMap.put(CellUtil.BORDER_RIGHT, value);
		return this;
	}

	public CellStyleMapBuilder borderTop(BorderStyle value) {
		cellStyleMap.put(CellUtil.BORDER_TOP, value);
		return this;
	}

	public CellStyleMapBuilder bottomBorderColor(short value) {
		cellStyleMap.put(CellUtil.BOTTOM_BORDER_COLOR, value);
		return this;
	}

	public CellStyleMapBuilder dataFormat(short value) {
		cellStyleMap.put(CellUtil.DATA_FORMAT, value);
		return this;
	}

	public CellStyleMapBuilder fillPattern(FillPatternType value) {
		cellStyleMap.put(CellUtil.FILL_PATTERN, value);
		return this;
	}

	public CellStyleMapBuilder fillForegroundColor(short value) {
		cellStyleMap.put(CellUtil.FILL_FOREGROUND_COLOR, value);
		return this;
	}

	public CellStyleMapBuilder fillBackgroundColor(short value) {
		cellStyleMap.put(CellUtil.FILL_BACKGROUND_COLOR, value);
		return this;
	}

	public CellStyleMapBuilder fillForegroundColorColor(Color value) {
		cellStyleMap.put(CellUtil.FILL_FOREGROUND_COLOR_COLOR, value);
		return this;
	}

	public CellStyleMapBuilder fillBackgroundColorColor(Color value) {
		cellStyleMap.put(CellUtil.FILL_BACKGROUND_COLOR_COLOR, value);
		return this;
	}

	public CellStyleMapBuilder fontIndex(int value) {
		cellStyleMap.put(CellUtil.FONT, value);
		return this;
	}

	public CellStyleMapBuilder hidden(boolean value) {
		cellStyleMap.put(CellUtil.HIDDEN, value);
		return this;
	}

	public CellStyleMapBuilder indention(short value) {
		cellStyleMap.put(CellUtil.INDENTION, value);
		return this;
	}

	public CellStyleMapBuilder leftBorderColor(short value) {
		cellStyleMap.put(CellUtil.LEFT_BORDER_COLOR, value);
		return this;
	}

	public CellStyleMapBuilder locked(boolean value) {
		cellStyleMap.put(CellUtil.LOCKED, value);
		return this;
	}

	public CellStyleMapBuilder rightBorderColor(short value) {
		cellStyleMap.put(CellUtil.RIGHT_BORDER_COLOR, value);
		return this;
	}

	public CellStyleMapBuilder rotation(short value) {
		cellStyleMap.put(CellUtil.ROTATION, value);
		return this;
	}

	public CellStyleMapBuilder topBorderColor(short value) {
		cellStyleMap.put(CellUtil.TOP_BORDER_COLOR, value);
		return this;
	}

	public CellStyleMapBuilder wrapText(boolean value) {
		cellStyleMap.put(CellUtil.WRAP_TEXT, value);
		return this;
	}

	public CellStyleMapBuilder shrinkToFit(boolean value) {
		cellStyleMap.put(CellUtil.SHRINK_TO_FIT, value);
		return this;
	}

	public CellStyleMapBuilder quotePrefixed(boolean value) {
		cellStyleMap.put(CellUtil.QUOTE_PREFIXED, value);
		return this;
	}
}
