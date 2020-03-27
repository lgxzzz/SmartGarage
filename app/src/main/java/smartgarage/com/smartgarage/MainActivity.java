package smartgarage.com.smartgarage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import smartgarage.com.smartgarage.fragment.AboutFragment;
import smartgarage.com.smartgarage.util.FragmentUtils;

public class MainActivity extends BaseActivtiy {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        showFragment(R.id.bottom_menu_about);
    }

    public void init(){

    }


    /**
     * 根据id显示相应的页面
     * @param menu_id
     */
    private void showFragment(int menu_id) {
        switch (menu_id){
            case R.id.bottom_menu_about:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, AboutFragment.getInstance(),R.id.main_frame);
                break;
//            case R.id.bottom_menu_msg:
//                FragmentUtils.replaceFragmentToActivity(fragmentManager, AnswerBookFragment.getInstance(),R.id.main_frame);
//                break;
//            case R.id.bottom_menu_about:
//                FragmentUtils.replaceFragmentToActivity(fragmentManager, AboutFragment.getInstance(),R.id.main_frame);
//                break;

        }
    }
}
