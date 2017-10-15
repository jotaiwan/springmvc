<nav class="[ navbar navbar-fixed-top ][ navbar-bootsnipp animate ]" role="navigation">
    <div class="[ container ]">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="[ navbar-header ]">
            <button type="button" class="[ navbar-toggle ]" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="[ sr-only ]">Toggle navigation</span>
                <span class="[ icon-bar ]"></span>
                <span class="[ icon-bar ]"></span>
                <span class="[ icon-bar ]"></span>
            </button>
            <div class="[ animbrand ]">
                <a class="[ navbar-brand ][ animate ]" href="#">CHEN</a>
            </div>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="[ collapse navbar-collapse ]" id="bs-example-navbar-collapse-1">
            <ul class="[ nav navbar-nav navbar-right ]">
                <li class="[ visible-xs ]">
                    <form action="http://bootsnipp.com/search" method="GET" role="search">
                        <div class="[ input-group ]">
                            <input type="text" class="[ form-control ]" name="q" placeholder="Search">
                            <span class="[ input-group-btn ]">
									<button class="[ btn btn-primary ]" type="submit"><span class="[ glyphicon glyphicon-search ]"></span></button>
									<button class="[ btn btn-danger ]" type="reset"><span class="[ glyphicon glyphicon-remove ]"></span></button>
								</span>
                        </div>
                    </form>
                </li>
                <li><a href="/" class="[ animate ]">Home</a></li>
                <li>
                    <a href="#" class="[ dropdown-toggle ][ animate ]" data-toggle="dropdown">Examples <span class="[ caret ]"></span></a>
                    <ul class="[ dropdown-menu ]" role="menu">
                        <li><a href="/members" class="[ animate ]">members <span class="[ pull-right glyphicon glyphicon-tasks ]"></span></a></li>
                        <li><a href="/login/all" class="[ animate ]">login manager <span class="[ pull-right glyphicon glyphicon-tasks ]"></span></a></li>
                        <li><a href="/employees" class="[ animate ]">employees <span class="[ pull-right glyphicon glyphicon-tasks ]"></span></a></li>
                        <li class="[ dropdown-header ]">--------</li>
                        <li><a href="/concert/aba" class="[ animate ]">concert - aba <span class="[ pull-right glyphicon glyphicon-tasks ]"></span></a></li>
                        <li><a href="/dessert/default" class="[ animate ]">dessert - default <span class="[ pull-right glyphicon glyphicon-tasks ]"></span></a></li>
                        <li class="[ dropdown-header ]">--------</li>
                        <li><a href="/shoppingCart" class="[ animate ]">shoppingcart <span class="[ pull-right glyphicon glyphicon-shopping-cart ]"></span></a></li>
                        <li><a href="/products" class="[ animate ]">products <span class="[ pull-right glyphicon glyphicon-tasks ]"></span></a></li>
                        <li><a href="/file/upload" class="[ animate ]">file uploader <span class="[ pull-right glyphicon glyphicon-tasks ]"></span></a></li>
                        <li><a href="/user/list" class="[ animate ]">user lists <span class="[ pull-right glyphicon glyphicon-tasks ]"></span></a></li>
                    </ul>
                </li>
                <li class="[ dropdown ]">
                    <a href="#" class="[ dropdown-toggle ][ animate ]" data-toggle="dropdown">System <span class="[ caret ]"></span></a>
                    <ul class="[ dropdown-menu ]" role="menu">
                        <li class="[ dropdown-header ]">User <span class="[ pull-right glyphicon glyphicon-user ]"></span></li>
                        <li><a href="/user/all" class="[ animate ]">Management <span class="[ pull-right glyphicon glyphicon-th-list ]"></span></a></li>
                        <li class="[ dropdown-header ]">Products <span class="[ pull-right glyphicon glyphicon-blackboard ]"></span></li>
                        <li><a href="#" class="[ animate ]">New <span class="[ pull-right glyphicon glyphicon-plus ]"></span></a></li>
                        <li><a href="#" class="[ animate ]">Edit <span class="[ pull-right glyphicon glyphicon-edit ]"></span></a></li>
                    </ul>
                </li>
                <li><a class="animate" href="/user/register/account">Register</a></li>
                <%--
                <li><a class="animate" href="/user/login">Login</a></li>
                --%>
                <li class="[ hidden-xs ]"><a href="#toggle-search" class="[ animate ]"><span class="[ glyphicon glyphicon-search ]"></span></a></li>
            </ul>
        </div>
    </div>
    <div class="[ bootsnipp-search animate ]">
        <div class="[ container ]">
            <form action="http://bootsnipp.com/search" method="GET" role="search">
                <div class="[ input-group ]">
                    <input type="text" class="[ form-control ]" name="q" placeholder="Search keywords">
                    <span class="[ input-group-btn ]">
							<button class="[ btn btn-danger ]" type="reset"><span class="[ glyphicon glyphicon-remove ]"></span></button>
						</span>
                </div>
            </form>
        </div>
    </div>
</nav>