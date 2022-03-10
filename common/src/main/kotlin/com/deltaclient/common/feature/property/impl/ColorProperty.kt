package com.deltaclient.common.feature.property.impl

import com.deltaclient.common.feature.property.AbstractProperty
import java.awt.Color

class ColorProperty(name: String, override var value: Color) : AbstractProperty<Color>(name)