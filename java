public void basics() throws Exception {
  WorkflowJob p = r.jenkins.createProject(WorkflowJob.class, "p");
  p.setDefinition(new CpsFlowDefinition(
    "node {\n" +
    "  writeFile text: 'println(21*2)', file: 'test.groovy'\n" +
    "  println 'something printed'\n" +// make sure that 'println' in groovy script works
    "  load 'test.groovy'\n" +
    "}", true));
  WorkflowRun b = r.assertBuildStatusSuccess(p.scheduleBuild2(0));
  r.assertLogContains("something printed", b);
  r.assertLogContains("42", b);
}
