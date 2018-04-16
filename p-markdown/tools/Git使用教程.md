[Git教程-菜鸟教程-简单明了](http://www.runoob.com/git/git-tutorial.html)  
[Git教程-易百教程-详细深入](https://www.yiibai.com/git/git_pull.html)

# 以下写个简单方便查阅的教程

## 提交代码流程
1. **git status -s**   
	查看在你上次提交之后是否有修改，加-s以简短的方式输出
2.  **git add .**   
	命令*git add （文件名）*可将该文件添加到缓存,git add .添加全部文件
3. **git commit -m '第一次版本提交'**   
	使用 git add 命令将想要快照的内容写入缓存区， 而执行 git commit 将缓存区内容添加到本地仓库中  
	如果没有全局配置账号密码，需要手动输入账号密码
4. **git remote -v**   
	查看远程分支，执行时加上 -v 参数，你还可以看到每个别名的实际链接地址。
5. **git pull [options] [<repository> [<refspec>…]]**  
	git pull <远程主机名> <远程分支名>:<本地分支名>  
	比如，要取回origin主机的next分支，与本地的master分支合并，需要写成下面这样 
	$ git pull origin next:master
	如果远程分支(next)要与当前分支合并，则冒号后面的部分可以省略。上面命令可以简写为：
	*$ git pull origin next*
6. **git push [alias] [branch]**   
	以上命令将你的 [branch] 分支推送成为 [alias] 远程仓库上的 [branch] 分支   
	如：*git push origin master*  

## git pull 撤销误操作  

> 本来想把github上的newpbft合并到本地的newpbft分支上，由于没有查看当前分支，直接运用git pull origin newpbft，结果将newpbft合并到了master分支中。

**解决方法**

> 1. 运行git reflog命令查看你的历史变更记录，如下：

```
fdb70fe HEAD@{0}: pull origin newpbft: Fast-forward
40a9a83 HEAD@{1}: checkout: moving from guan to master
b3fa4c3 HEAD@{2}: commit: copy from newpbft, first init
71bf0ec HEAD@{3}: checkout: moving from newpbft to guan
71bf0ec HEAD@{4}: commit: 1. add moveStore() to clean up certStore and blockStore.
1006d67 HEAD@{5}: commit: 1. Add PBFT branch to Puppeth.
fa3fb56 HEAD@{6}: commit: 1. change some errors about packages and vars
5f40fdc HEAD@{7}: checkout: moving from master to newpbft
40a9a83 HEAD@{8}: clone: from https://github.com/yeongchingtarn/geth-pbft.git
```

> 2. 然后用git reset --hard HEAD@{n}，（n是你要回退到的引用位置）回退。
比如上图可运行 git reset --hard 40a9a83

**注意：回退后本地的所有修改都没了哦**

## 其他操作
1. **创建分支**		
> git branch [branch name]  

1. **切换分支**		
> git checkout [branch name]  

1. **删除分支**		
> git branch -D [branch name]


4. **重命名分支**  

```
$ git branch
* master
  new_branch

Administrator@MY-PC /D/worksp/sample (master)
$ git branch -m new_branch wchar_support

``` 

## 提交流程
```
git add .

git commit -m "Feat: 分享跳转需求代码"
//git commit -m "Fix: 分享跳转需求代码BUG"

git rebase origin/develop

git push origin feature/GYZQE-ITE-12


``` 


## 简易的命令行入门教程:

### Git 全局设置:

```
git config --global user.name "Hu_Care"
git config --global user.email "1015918929@qq.com"
```

### 创建 git 仓库:

```
mkdir MVPStudy
cd MVPStudy
git init
touch README.md
git add README.md
git commit -m "first commit"
git remote add origin https://gitee.com/hshare/MVPStudy.git
git push -u origin master
```

### 已有项目?

```
cd existing_git_repo
git remote add origin https://gitee.com/hshare/MVPStudy.git
git push -u origin master
```