package com.djinfinite.manors_bounty.util

import kotlin.time.Duration

val Duration.inWholeTicks: Int get() = (inWholeSeconds * 20).toInt()