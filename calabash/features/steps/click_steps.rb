假設(/^我等到看到文字 "([^"]*)"$/) do |mark|
  wait_for_element_exists("* marked:'#{mark}'")
end

當(/^我按了 "([^"]*)"$/) do |mark|
  wait_for_elements_exist("* marked:'#{mark}'")
  touch("* marked:'#{mark}'")
end