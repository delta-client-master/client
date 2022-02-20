package com.deltaclient.common.feature.property.impl

import com.deltaclient.common.feature.property.AbstractProperty

class IntProperty(name: String, override var value: Int) : AbstractProperty<Int>(name)