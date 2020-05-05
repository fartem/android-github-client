package com.smlnskgmail.jaman.githubclient

import com.smlnskgmail.jaman.githubclient.entities.GitHubExpandedProfileTest
import com.smlnskgmail.jaman.githubclient.entities.GitHubRepositoryTest
import com.smlnskgmail.jaman.githubclient.entities.GitHubShortProfileTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@Suite.SuiteClasses(
    GitHubExpandedProfileTest::class,
    GitHubShortProfileTest::class,
    GitHubRepositoryTest::class
)
@RunWith(Suite::class)
class TestSuite
