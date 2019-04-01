package com.model.gridcomponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import com.constants.GridComponentConstants;
import com.utils.JSONUtils;
import com.utils.ValidationUtils;

public class StandardExtraParams implements Serializable, GridComponentConstants {

	private static final long serialVersionUID = 9057134003769254308L;
	
	private List<String> secureActionColumnButtons = new ArrayList<String>(); 
	private List<String> secureSelectionColumnButtons = new ArrayList<String>();
	
	public StandardExtraParams(final JsonObject otherParamsAsJSONObject) {
		final Boolean hasActionButtons = JSONUtils.getValueFromJSONObject(otherParamsAsJSONObject, STANDARD_EXTRA_PARAM_HAS_ACTION_BUTTONS, Boolean.class);
		if (ValidationUtils.checkObjectAvailability(hasActionButtons) && hasActionButtons) {
			final JsonArray secureActionColumnButtonsJSONArray = JSONUtils.getValueFromJSONObject(otherParamsAsJSONObject, STANDARD_EXTRA_PARAM_SECURE_ACTION_COLUMN_BUTTONS, JsonArray.class);
			if (ValidationUtils.checkObjectAvailability(secureActionColumnButtonsJSONArray)) {
				for (Object secureActionColumnButtonObject : secureActionColumnButtonsJSONArray) {
					final String secureButton = secureActionColumnButtonObject.toString().replaceAll(INVERTED_COMMA, EMPTY_STRING);
					this.secureActionColumnButtons.add(secureButton);
				}
			}
		}
		final Boolean hasSelectionButtons = JSONUtils.getValueFromJSONObject(otherParamsAsJSONObject, STANDARD_EXTRA_PARAM_HAS_SELECTION_BUTTONS, Boolean.class);
		if (ValidationUtils.checkObjectAvailability(hasSelectionButtons) && hasSelectionButtons) {
			final JsonArray secureSelectionColumnButtonsJSONArray = JSONUtils.getValueFromJSONObject(otherParamsAsJSONObject, STANDARD_EXTRA_PARAM_SECURE_SELECTION_COLUMN_BUTTONS, JsonArray.class);
			if (ValidationUtils.checkObjectAvailability(secureSelectionColumnButtonsJSONArray)) {
				for (Object secureSelectionColumnButtonObject : secureSelectionColumnButtonsJSONArray) {
					final String secureButton = secureSelectionColumnButtonObject.toString().replaceAll(INVERTED_COMMA, EMPTY_STRING);
					this.secureSelectionColumnButtons.add(secureButton);
				}
			}
		}
	}
	
	public List<String> getSecureActionColumnButtons() {
		return secureActionColumnButtons;
	}
	
	public List<String> getSecureSelectionColumnButtons() {
		return secureSelectionColumnButtons;
	} 
}
