/*
 * Copyright (c) 2016. Behrouz Khezry
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.hive;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.bkhezry.extrawebview.AppUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @StyleRes int style = R.style.AppTheme_Dark;
        setTheme(style);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |
                ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        String versionName = "";
        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // do nothing
        }
        setTextWithLinks(R.id.text_application_info, getString(R.string.application_info_text, versionName));
        setTextWithLinks(R.id.text_developer_info, getString(R.string.developer_info_text));
        setTextWithLinks(R.id.text_libraries, getString(R.string.libraries_text));
        setTextWithLinks(R.id.text_license, getString(R.string.license_text));
    }

    private void setTextWithLinks(@IdRes int textViewResId, String htmlText) {
        AppUtils.setTextWithLinks((TextView) findViewById(textViewResId), AppUtils.fromHtml(htmlText));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
