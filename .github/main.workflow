workflow "New workflow" {
  on = "push"
  resolves = ["Gradle test"]
}

action "Gradle test" {
  uses = "MrRamych/gradle-actions@master"
  args = "test"
}
