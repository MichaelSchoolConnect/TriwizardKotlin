
###ReadMe###
1. Activity observes LiveData from ViewModel
2. ViewModel observes LiveData from Repository. 
3. Changes are made in the repository, which are pushed to its LiveData. 
4. Those changes are delivered to the Activity (through the ViewModel).