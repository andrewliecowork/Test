require 'calabash-cucumber/ibase'

class NasListPage < Calabash::IBase

  def title
    "Home"
  end

  def logout
    touch("UITabBarButton marked:'Settings'")
    sleep(1)
    touch("* marked:'Log Out'")
    sleep(1)
    touch("* marked:'OK'")
    page(LoginPage).await(timeout: 5)
  end

end
