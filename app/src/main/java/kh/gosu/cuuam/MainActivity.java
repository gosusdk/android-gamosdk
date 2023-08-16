package kh.gosu.cuuam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.game.gstracking.GTrackingManger;
import com.game.nsdk.inteface.IGameInitListener;
import com.game.nsdk.inteface.IGameOauthListener;
import com.game.nsdk.inteface.IGamePaymentListener;
import com.game.nsdk.inteface.OnSingleClickListener;
import com.game.nsdk.object.GameItemIAPObject;
import com.game.nsdk.utils.GameConstant;
import com.game.nsdk.utils.GameException;
import com.game.nsdk.utils.GameSDK;
import com.game.nsdk.utils.GameUtils;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private Button btnDangNhap;
    private Button btnTTTK;
    private Button btnDSITEM;
    private Button btnTTITEM1;
    private Button btnDangXuat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSDK();

        initView();

        Context context = getApplicationContext();

        Log.d("T123", Locale.getDefault().getLanguage());

    }

    public void setupSDK() {
        //
        GameSDK.sdkInitialize(this,  new IGameInitListener() {
            @Override
            public void onSuccess() {

                //GameSDK.setLanguage("");

                onLogin();
            }

            @Override
            public void onError(GameException exception) {
                exception.printStackTrace();
            }
        });
    }

    private void onLogin() {
        GameSDK.onLogin(new IGameOauthListener() {
            @Override
            public void onLoginSuccess(String UserId, String UserName, String accesstoken) {
                Log.d("onLoginSuccess", "dang nhap thanh cong" + UserName);
                btnDangNhap.setVisibility(View.GONE);
                btnDSITEM.setVisibility(View.VISIBLE);
                btnTTITEM1.setVisibility(View.VISIBLE);
                btnDangXuat.setVisibility(View.VISIBLE);

                callTrackingExample();
            }

            @Override
            public void onLogout() {
                btnDangNhap.setVisibility(View.GONE);
                btnDSITEM.setVisibility(View.GONE);
                btnTTITEM1.setVisibility(View.GONE);
                btnDangXuat.setVisibility(View.GONE);


                GameSDK.showLogin();
            }

            @Override
            public void onError() {

            }
        });
    }

    public void initView() {
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View var1) {
                GameSDK.showLogin();
            }
        });


        btnDSITEM = (Button) findViewById(R.id.btnDSITEM);
        btnDSITEM.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                for(int i = 0; i < GameConstant.iap_product_ids.size(); i++){
                    Log.d("TAG_ITEM", GameConstant.iap_product_ids.get(i)+"");
                }
            }
        });



        btnTTITEM1 = (Button) findViewById(R.id.btnTTITEM1);
        btnTTITEM1.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View var1) {
                //Log.d("T11", GameSDK.version());
//                if(GameConstant.iap_product_ids.size() <= 0) return;
                //Log.d("SDK", GameConstant.iap_product_ids.get(0)); "android.test.purchased";
//                Log.d("SDK", GameConstant.iap_product_ids.get(0)+"");
                String productID = "thb.stand.gg.pack8";
                String mProductName = "Mua gói 100KNB";
                String currencyUnit = "USD";
                String amount = "22000";
                String serverID       = "S1";
                String characterID    = "Character_ID";
                String extraInfo    = "";

                GameItemIAPObject gosuItemIAPObject = new GameItemIAPObject(productID, mProductName, currencyUnit, amount, serverID, characterID, extraInfo);


                GameSDK.payment(gosuItemIAPObject, new IGamePaymentListener() {
                    @Override
                    public void onPaymentSuccess(String message) {
                        Log.d("T123", message);
                    }

                    @Override
                    public void onPaymentError(String message) {
                        Log.d("T123", message);

                    }
                });
            }
        });

        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
        btnDangXuat.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View var1) {
                GameSDK.logout();
            }
        });

        btnDangNhap.setVisibility(View.VISIBLE);
        btnDSITEM.setVisibility(View.GONE);
        btnTTITEM1.setVisibility(View.GONE);
        btnDangXuat.setVisibility(View.GONE);


    }

    protected void callTrackingExample()
    {
        GTrackingManger.getInstance().trackingStartTrial();
        GTrackingManger.getInstance().trackingTutorialCompleted();
        GTrackingManger.getInstance().doneNRU(
            "server_id",
            "role_id",
            "Role Name"
        );
        /* custom event */
        GTrackingManger.getInstance().trackingEvent("level_20");
        GTrackingManger.getInstance().trackingEvent("level_20", "{\"customer_id\":\"1234\"}");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        GameSDK.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}