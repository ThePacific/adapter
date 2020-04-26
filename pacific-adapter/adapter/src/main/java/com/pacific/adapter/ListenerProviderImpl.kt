/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pacific.adapter

import android.view.View
import android.view.View.OnLongClickListener
import android.view.View.OnTouchListener
import android.widget.CompoundButton

internal class ListenerProviderImpl : ListenerProvider {

    override var onClickListener: View.OnClickListener? = null

    override var onTouchListener: OnTouchListener? = null

    override var onLongClickListener: OnLongClickListener? = null

    override var onCheckedChangeListener: CompoundButton.OnCheckedChangeListener? = null

    override var imageLoader: AdapterImageLoader? = null

    override var textChangedListener: AdapterTextWatcher? = null
}