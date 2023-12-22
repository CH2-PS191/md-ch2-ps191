package com.example.empaq

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.empaq.data.EmpaqRepository
import com.example.empaq.data.retrofit.ApiConfig
import com.example.empaq.ui.navigation.NavigationItem
import com.example.empaq.ui.navigation.Screen
import com.example.empaq.ui.screen.ViewModelFactory
import com.example.empaq.ui.screen.authentication.ForgotPasswordScreen
import com.example.empaq.ui.screen.authentication.LoginScreen
import com.example.empaq.ui.screen.authentication.RegisterScreen
import com.example.empaq.ui.screen.chatbot.ChatbotScreen
import com.example.empaq.ui.screen.detail.DetailProfileScreen
import com.example.empaq.ui.screen.moreoptionprofile.HelpandSupportScreen
import com.example.empaq.ui.screen.home.HomeScreen
import com.example.empaq.ui.screen.moreoptionprofile.AboutAppScreen
import com.example.empaq.ui.screen.profile.ProfileScreen
import com.google.firebase.auth.FirebaseAuth

import com.example.empaq.ui.screen.pakar.SpecialistScreen
import com.example.empaq.ui.screen.roomchatlist.RoomchatListKonselorScreen
import com.example.empaq.ui.screen.roomchatlist.RoomchatListPakarScreen
import com.example.empaq.ui.screen.roomchatlist.RoomchatUserScreen
import com.example.empaq.ui.screen.roomchatlist.RoomchatUserViewModel
import com.example.empaq.ui.screen.sebaya.KonselorSebayaScreen

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EMPAQApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    var topAppBarTitle by remember { mutableStateOf("EMPAQ") }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val roomchatUserViewModel: RoomchatUserViewModel = viewModel(
        factory = ViewModelFactory(EmpaqRepository(ApiConfig().getApiService()))
    )

    Scaffold(
        modifier = Modifier,
        topBar = {
            if (currentRoute != Screen.Login.route && currentRoute != Screen.Register.route && currentRoute != Screen.ForgotPassword.route && currentRoute != Screen.Main.route) {
                CenterAlignedTopAppBar(
                    title = {
                        if (currentRoute != Screen.Home.route) {
                            Text(
                                text = topAppBarTitle,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .wrapContentHeight(CenterVertically),
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                            )
                        } else {
                            Image(painter = painterResource(id = R.drawable.logo_w_empaq_black_text), contentDescription = stringResource(R.string.logo_empaq_desc))
                        }
                    },
                    navigationIcon = {
                        if (currentRoute == Screen.Chatbot.route ||
                            currentRoute == Screen.DetailProfile.route ||
                            currentRoute == Screen.Specialist.route ||
                            currentRoute == Screen.Konselor.route ||
                            currentRoute == Screen.ConversationsKonselor.route ||
                            currentRoute == Screen.ConversationsPakar.route ||
                            currentRoute == Screen.HelpnSupport.route ||
                            currentRoute == Screen.About.route ||
                            currentRoute == Screen.RoomchatUser.route
                            ) {
                            IconButton(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .wrapContentHeight(Alignment.CenterVertically)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.KeyboardArrowLeft,
                                    contentDescription = stringResource(R.string.back_desc),
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    },
                    actions = {
                              if (currentRoute == Screen.Konselor.route || currentRoute == Screen.Specialist.route) {
                                  IconButton(
                                      onClick = {
                                          if (currentRoute == Screen.Konselor.route) {
                                              navController.navigate(Screen.ConversationsKonselor.route)
                                          } else if (currentRoute == Screen.Specialist.route) {
                                              navController.navigate(Screen.ConversationsPakar.route)
                                          }
                                      },
                                      modifier = Modifier
                                          .fillMaxHeight()
                                          .wrapContentHeight(Alignment.CenterVertically)
                                  ) {
                                      Icon(
                                          imageVector = Icons.Default.Chat,
                                          contentDescription = "List Roomchat",
                                          modifier = Modifier.fillMaxSize()
                                      )
                                  }
                              }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .shadow(elevation = 4.dp),
                )
            }
        },
        bottomBar = {
            if (
                currentRoute != Screen.Chatbot.route &&
                currentRoute != Screen.DetailProfile.route &&
                currentRoute != Screen.Specialist.route &&
                currentRoute != Screen.Konselor.route &&
                currentRoute != Screen.Login.route &&
                currentRoute != Screen.Register.route &&
                currentRoute != Screen.ForgotPassword.route &&
                currentRoute != Screen.ConversationsKonselor.route &&
                currentRoute != Screen.ConversationsPakar.route &&
                currentRoute != Screen.RoomchatUser.route &&
                currentRoute != Screen.HelpnSupport.route
                ) {
                BottomBar(navController = navController)
            }
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Main.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Main.route){
                topAppBarTitle = ""
                CheckAuthenticationAndNavigate(navController = navController)
            }
            composable(Screen.Chatbot.route){
                topAppBarTitle = stringResource(R.string.empaq_bot_title)
                ChatbotScreen()
            }
            composable(Screen.Home.route){
                topAppBarTitle = stringResource(R.string.app_name)
                HomeScreen(
                    navigateToProfile = {
                        navController.popBackStack()
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    navigateToChatbot = {
                        navController.navigate(Screen.Chatbot.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    navigateToSpecialist = {
                        navController.navigate(Screen.Specialist.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    navigateToKonselor = {
                        navController.navigate(Screen.Konselor.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    navigateToLogin = {
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Screen.Profile.route){
                topAppBarTitle = stringResource(R.string.profile_title)
                ProfileScreen(
                    navigateToDetail = {
                        navController.navigate(Screen.DetailProfile.route) {
                            popUpTo(Screen.Profile.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    logout = {
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Main.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    helpAndSupport = {
                        navController.navigate(Screen.HelpnSupport.route) {
                            popUpTo(Screen.Profile.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    aboutApp = {
                        navController.navigate(Screen.About.route) {
                            popUpTo(Screen.Profile.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable(Screen.DetailProfile.route) {
                topAppBarTitle = "UBAH PROFILE"
                DetailProfileScreen(
                    navigateToProfile = {
                        navController.popBackStack()
                        navController.navigate(Screen.Profile.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
            composable(Screen.Specialist.route) {
                topAppBarTitle = "PAKAR AHLI"
                SpecialistScreen()
            }
            composable(Screen.Konselor.route) {
                topAppBarTitle = "KONSELOR SEBAYA"
                KonselorSebayaScreen()
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    navigateToRemember = {
                        navController.navigate(Screen.ForgotPassword.route) {
                            popUpTo(Screen.Login.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    },
                    navigateToRegister = {
                        navController.navigate(Screen.Register.route) {
//                            popUpTo(Screen.Login.route) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
                        }
                    },
                    finishLogin = {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(
                    finishRegister = {
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
                    },
                    navigateToRemember = {
                        navController.popBackStack()
                        navController.navigate(Screen.ForgotPassword.route)
                    },
                    navigateToLogin = {
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
                    }
                )
            }
            composable(Screen.ForgotPassword.route) {
                ForgotPasswordScreen(
                    navigateToLogin = {
                        navController.popBackStack()
                        navController.navigate(Screen.Login.route)
                    },
                    navigateToRegister = {
                        navController.popBackStack()
                        navController.navigate(Screen.Register.route)
                    },
                )
            }
            composable(Screen.ConversationsKonselor.route) {
                topAppBarTitle = "List Roomchat"
                RoomchatListKonselorScreen(
                    navigateToRoomchat = {
                        navController.navigate(Screen.RoomchatUser.route)
                    },
                    roomchatUserViewModel = roomchatUserViewModel
                )
            }
            composable(Screen.ConversationsPakar.route) {
                topAppBarTitle = "List Roomchat"
                RoomchatListPakarScreen(
                    navigateToRoomchat = {
                        navController.navigate(Screen.RoomchatUser.route)
                    },
                    roomchatUserViewModel = roomchatUserViewModel
                )
            }
            composable(Screen.RoomchatUser.route) {
                topAppBarTitle = "ROOM CHAT"
                RoomchatUserScreen(roomchatUserViewModel = roomchatUserViewModel)
            }
            composable(Screen.HelpnSupport.route) {
                topAppBarTitle = "HELP & SUPPORT"
                HelpandSupportScreen()
            }
            composable(Screen.About.route) {
                topAppBarTitle = "ABOUT"
                AboutAppScreen()
            }
        }

    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .clipToBounds(),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_chatbot),
                icon = Icons.Default.MailOutline,
                screen = Screen.Chatbot
            ),
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun CheckAuthenticationAndNavigate(navController: NavController) {
    val user = FirebaseAuth.getInstance().currentUser

    if (user != null) {
        // User is authenticated, navigate to home screen
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    } else {
        // User is not authenticated, navigate to authentication screen
        navController.popBackStack()
        navController.navigate(Screen.Login.route) {
            launchSingleTop = true
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun EMPAQAppPreview() {
    EMPAQApp()
}