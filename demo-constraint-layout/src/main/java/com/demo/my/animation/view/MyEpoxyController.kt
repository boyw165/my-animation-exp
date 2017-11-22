//  Copyright Nov 2017-present boyw165@gmail.com
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.

package com.demo.my.animation.view

import com.airbnb.epoxy.TypedEpoxyController

class MyEpoxyController(numOfModels: Int) : TypedEpoxyController<Int>() {

    init {
        setData(numOfModels)
    }

    override fun buildModels(numOfModels: Int) {
        // Create n models.
        var count = numOfModels
        while (count > 0) {
            MyEpoxyModel()
                .id(count)
                .addTo(this)
            --count
        }
    }
}
