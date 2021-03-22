package com.bagas.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var tvName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvCompany: TextView
    private lateinit var tvFollower: TextView
    private lateinit var tvFollowing: TextView
    private lateinit var tvRepo: TextView
    private lateinit var tvAvatar: CircleImageView

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        tvAvatar = findViewById(R.id.tv_avatar)
        tvName = findViewById(R.id.tv_name)
        tvUsername = findViewById(R.id.tv_username)
        tvLocation = findViewById(R.id.tv_location)
        tvCompany = findViewById(R.id.tv_company)
        tvFollower = findViewById(R.id.tv_followers)
        tvFollowing = findViewById(R.id.tv_following)
        tvRepo = findViewById(R.id.tv_repo)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        var username = user.username
        var name = user.name
        var location = user.location
        var repo = user.repository
        var company = user.company
        var follower = user.followers
        var following = user.following
        var avatar = user.avatar

        tvAvatar.setImageResource(avatar)
        tvName.text = name
        tvUsername.text = username
        tvLocation.text = location
        tvCompany.text = company
        tvFollowing.text = following.toString()
        tvFollower.text = follower.toString()
        tvRepo.text = repo.toString()

        setActionBarTitle("Detail User")

    }
}