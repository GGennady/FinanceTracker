package com.example.graphs.circle_graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun CircleGraph(
    entries: List<CircleGraphElement>,
    modifier: Modifier = Modifier
) {
    val totalSweep = 360f
    var startAngle = -90f
    val strokeWidth = 48f

    Row(modifier = modifier.padding(16.dp)) {
        Canvas(
            modifier = Modifier
                .size(200.dp)
                .padding(8.dp)
        ) {
            entries.forEach { entry ->
                val sweep = (entry.percentage / 100) * totalSweep
                drawArc(
                    color = entry.color,
                    startAngle = startAngle,
                    sweepAngle = sweep,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                )
                startAngle += sweep
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            entries.forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Canvas(Modifier.size(6.dp)) {
                        drawCircle(it.color)
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(text = "${it.percentage.roundToInt()}% ${it.label}", fontSize = 10.sp,)
                }
            }
        }
    }
}