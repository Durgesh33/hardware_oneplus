/*
* Copyright (C) 2016 The OmniROM Project
* Copyright (C) 2021-2022 The Evolution X Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package org.lineageos.oneplus.DeviceExtras.modeswitch;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;

import org.lineageos.oneplus.DeviceExtras.DeviceExtras;
import org.lineageos.oneplus.DeviceExtras.FileUtils;
import org.lineageos.oneplus.DeviceExtras.R;

public class PowerEfficientWorkqueueModeSwitch implements OnPreferenceChangeListener {

    private static final int NODE = R.string.node_power_efficient_workqueue_mode_switch;

    public static String getFile(Context context) {
        String file = context.getString(NODE);
        if (FileUtils.fileWritable(file)) {
            return file;
        }
        return null;
    }

    public static boolean isSupported(Context context) {
        return FileUtils.fileWritable(getFile(context));
    }

    public static boolean isCurrentlyEnabled(Context context) {
        return FileUtils.getFileValueAsBoolean(getFile(context), false);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Boolean enabled = (Boolean) newValue;
        FileUtils.writeValue(getFile(preference.getContext()), enabled ? "1" : "0");
        return true;
    }
}
