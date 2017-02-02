require 'calabash-cucumber/ibase'

class LoginPage < Calabash::IBase

  def trait
    "* marked:'login'"
  end

  def login(cloud_id, password)
    wait_for_element_exists("* marked:'login'")
    sleep(5)
    touch("* marked:'login'")

    enter_text("* marked:'cloud_id'", cloud_id)
    enter_text("* marked:'password'", password)

    wait_for_elements_exist("* marked:'sign_in'")
    touch("* marked:'sign_in'")

    wait_for_login_done
  end
     
  def wait_for_login_done
    wait_for_element_exists("* {text CONTAINS[c] 'Home'}")
    page(NasListPage)
  end

end
