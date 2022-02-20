package com.deltaclient.common.feature.property.impl

import com.deltaclient.common.feature.property.AbstractProperty

class BooleanProperty(name: String, override var value: Boolean) : AbstractProperty<Boolean>(name)