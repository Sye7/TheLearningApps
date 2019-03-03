package developers.hub.com.thelearningapp.Helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.model.HelpLiveo;
import br.liveo.navigationliveo.NavigationLiveo;
import developers.hub.com.thelearningapp.R;

public class home extends NavigationLiveo implements OnItemClickListener {


    private HelpLiveo mHelpLiveo;

    @Override
    public void onInt(Bundle savedInstanceState) {

        // Init



        // User Information
        this.userName.setText("Sasuke Uchiha");
        this.userEmail.setText("uchiha.me@gmail.com");
        this.userPhoto.setImageResource(R.drawable.chanchu);
        this.userBackground.setImageResource(R.drawable.ic_user_background_first);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.inbox), R.drawable.common_google_signin_btn_icon_dark, 7);
        mHelpLiveo.addSubHeader(getString(R.string.categories)); //Item subHeader
        mHelpLiveo.add(getString(R.string.starred), R.drawable.fui_ic_facebook_white_22dp);
        mHelpLiveo.add(getString(R.string.sent_mail), R.drawable.fui_idp_button_background_email);
        mHelpLiveo.add(getString(R.string.drafts), R.drawable.ic_menu_camera);
        mHelpLiveo.addSeparator(); // Item separator
        mHelpLiveo.add(getString(R.string.trash), R.drawable.common_google_signin_btn_icon_light);
        mHelpLiveo.add(getString(R.string.spam), R.drawable.ic_menu_share, 120);

        //with(this, Navigation.THEME_DARK). add theme dark
        //with(this, Navigation.THEME_LIGHT). add theme light

        with(this) // default theme is dark
                .startingPosition(2) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())
                .footerItem(R.string.settings, R.mipmap.logout)
                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();
    }


    @Override //The "R.id.container" should be used in "beginTransaction (). Replace"
    public void onItemClick(int position) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
//        Fragment mFragment = new FragmentCourse.newInstance(mHelpLiveo.get(position).getName());

        Fragment mFragment;
         switch(position){

             case 0:
                 mFragment = new FragmentCourse();
                 break;
             case 1:
                 mFragment = new FragmentCourse();
                 break;
                 default:
                     mFragment = new FragmentCourse();
                     break;
         }



        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

}
