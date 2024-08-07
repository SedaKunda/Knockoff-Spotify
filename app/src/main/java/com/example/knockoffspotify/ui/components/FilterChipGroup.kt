import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipGroup(
    modifier: Modifier = Modifier,
    onFilterSelected: (String) -> Unit
) {
    val filters = listOf("") //decide on filter params
    val selectedFilter = remember { mutableStateOf(filters[0]) }

    Row(modifier = modifier.padding(horizontal = 16.dp)) {
        filters.forEach { filter ->
            FilterChip(
                onClick = {
                    selectedFilter.value = filter
                    onFilterSelected(filter)
                },
                colors = InputChipDefaults.inputChipColors(
                    containerColor = if (selectedFilter.value == filter) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                ),
                label = {
                    Text(text = filter)
                },
                selected = selectedFilter.value == filter,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}