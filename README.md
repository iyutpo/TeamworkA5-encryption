# TeamworkA5

## Important Things
Nothing

## How to use git for this repo

The main branch of this repo is `develop`, which you can not push to directly.

A typical workflow of submitting your work to this repo:
1. checkout latest develop branch with `git checkout develop` then `git pull`
2. create a new feature branch `git checkout -b {your_feature_branch_name}`
3. add your code and pass the unit test
4. push the feature branch to github, a Pull Request (PR) will be created automatically. `git push origin "$(git_current_branch)"`  This command will output a PR link, which can be opened in a browser. 
5. Send the PR to other team members for review and get one approval. 
6. merge the PR by clicking `Squash and Merge` (the drop down of `Create Pull Request`).


If there's a conflict at step 6, you need to 
1. update local develop branch to latest `git fetch origin develop:develop`
2. rebase your feature branch to develop `git checkout -b {your_feature_branch_name}` `git rebase develop` and address the conflict during this step
3. force push your feature branch `git push origin "$(git_current_branch)" -f` and request review again.
