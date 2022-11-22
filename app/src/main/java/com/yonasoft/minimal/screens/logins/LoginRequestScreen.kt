package com.yonasoft.minimal.screens

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yonasoft.minimal.navigation.Screen
import com.yonasoft.minimal.ui.theme.Blue1
import com.yonasoft.minimal.ui.theme.Blue2


@Composable
fun LoginRequestScreen(navController: NavController) {
    val context = LocalContext.current
    var checked by rememberSaveable {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue1),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        //TODO: Login to MAL and navigate to Home
                        Login(context = context)
                        navController.navigate(Screen.HomeScreen.route)
                    }, modifier = Modifier.padding(bottom = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Blue2),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = "Login")
                }
                Button(
                    onClick = {
                        navController.navigate(Screen.HomeScreen.route)
                    },
                    modifier = Modifier.padding(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Blue2),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = "Skip")
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 84.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Checkbox(checked = checked, onCheckedChange = { checked = !checked })
                    Text(
                        text = "Don't show at startup. You can still login from he side bar!",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


fun Login(context: Context) {
    //Parameters
    val responseType = "code"
    val clientId = "2ebd5b69560a6724b07682d4f5400fe9"
    val codeChallenge = "47DEQpj8HBSa-_TImW-5JCeuQeRkm5NMpJWZG3hSuFU"
    val state = "Requestmmal1029"
    val codeVerifier =
        "-sQm3U3pEJeS~w2-IKJxBR-gVyrbS_B~M7H3sCtO9gYLNoAF9~HnNmIoTQ7AumgAQdhTCRVvgWdlP2ouIq_Ly.0LO3YxgX4fbxudbdf8K5k6GCXM69H3lD0l.MRjX0Fo"
    val codeChallengeMethod = "plain"
    val authCode = "def50200017b78f3776cc8265c0359f6a58a739c1218db1cdc9bd62cc0f109897e9609e528b202df1a3f00fd3d8cbc2aab54c29a7447067e0c8a0d194e875e516746cd6b0eff93631bf83b2a7992bf947150d0b77edb06f64420c49ac62019d57ec4dfa76867c58218af68fc00c035eb28a1245b450c7dea9091beab95a5768c2f0525885bbee7699fbe49c9055b57f19cb8598a5afd8e4a55209679f0d0b512ab88dcea89f752059719c8ffc35e713f4f56e149bba000b1fae9bb5f2fd7774c256a2cdbfe03624d1d022a2dfca896f4a427e7eced755e8ad5228c80ee0ea586b6275f690a2193d08624d89a30b8c26ab09a8b5200c85bd7b47321638e3ee65ec9d1a838c4aa79d790602f49d40d62e200c59686bb2812e4a74aed5da090a7f4a582660a0763b60c90298a36285b34d5cc4217f6144fc71778e0e6e61fbfd03b708e9ebed1736d297a18b2375285fc879ab3707256502768b03d28f1086db135244a3cc09d3ace501c99c41c2485f37e60a99949a468e59b638c1ef9d8af7db6bf6594b54d5411b4bd8f141338ace0aa08cb77f3d07d66ad570e51d7f6f4aa3dac805fab0227ba91d681eaafabb96088159fd295c02298678b"
    val grantType =  "authorization_code"
    val redirectUri = "https://myanimelist.net/profile/@me"

    //Authentication Uri
    val authUri = "https://myanimelist.net/v1/oauth2/authorize" +
            "?response_type=$responseType" +
            "&client_id=$clientId" +
            "&code_challenge=$codeChallenge" +
            "&state=$state" +
            "&redirect_uri=$redirectUri" +
            "&code_challenge_method=$codeChallengeMethod"

    //Token Uri
    val tokenUri = "https://myanimelist.net/v1/oauth2/token" +
            "?client_id=$clientId" +
            "&code=$authCode" +
            "&codeVerifier=$codeVerifier" +
            "&grantType=$grantType" +
            "&redirect_uri=$redirectUri"

    val customTabsServiceBuilder = CustomTabsIntent.Builder()


    customTabsServiceBuilder
        .build()
        .launchUrl(context, Uri.parse(authUri))

}



