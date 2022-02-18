package com.deltaclient.common.event.impl

import com.deltaclient.common.model.Cancellable

class ChatMessageSendEvent(val message: String) : Cancellable()