package com.example.cafesapp.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import com.example.cafesapp.NavigationBarElement

/**
 * The navigation rail content that navigates between the different screens.
 *
 * @param selectedDestination the selected destination.
 * @param onTabPressed the function to navigate to the page.
 */
@Composable
fun MyNavigationRail(
    selectedDestination: NavDestination?,
    onTabPressed: (String) -> Unit
) {
    NavigationRail {
        for (navItem in NavigationBarElement.values()) {
            NavigationRailItem(
                selected = selectedDestination?.route == navItem.name,
                onClick = { onTabPressed(navItem.name) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name
                    )
                }
            )
        }
    }
}
